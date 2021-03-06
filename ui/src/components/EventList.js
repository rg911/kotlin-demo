import React from 'react';
import moment from 'moment';
import { Link } from 'react-router-dom';

class EventListItem extends React.Component {
    render() {
        const { eventId, name, category, subCategory, startTime, markets } = this.props;
        return <tr>
            <td className="tc1" colSpan="2">{formatTime(startTime)}</td>
            <td className="tc2">
                <span>{category}</span>
            </td>
            <td className="tc2">
                <span>{subCategory}</span>
            </td>
            <td className="tc3" colSpan="2">
                <Link to={`/event/${eventId}`} title={formatName(name)}>
                    {formatName(name)}
                </Link>
            </td>
        </tr>;
    };
}
const formatName = (name) => {
    if (!name) return '';
    return name.replace(/\|/g, '');
}
const formatTime = (time) => {
  return moment(time).format('hh:mm:ss');
}

export default EventListItem;